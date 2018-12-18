package `in`.farmguide.myapplication.domainimpl

import `in`.farmguide.myapplication.domain.GetPostsUseCase
import `in`.farmguide.myapplication.repository.db.post.PostMinimal
import `in`.farmguide.myapplication.repository.db.post.PostsRepository
import `in`.farmguide.myapplication.work.AppWorkManager
import io.reactivex.Flowable
import javax.inject.Inject

class GetPostsUseCaseImpl @Inject constructor(
    private val workManager: AppWorkManager,
    private val postsRepository: PostsRepository
) : GetPostsUseCase {
    override fun getPosts(): Flowable<List<PostMinimal>> =
        postsRepository.getPosts().apply {
            fetchPosts()
        }

    private fun fetchPosts() {
        workManager.fetchAndSavePosts()
    }
}